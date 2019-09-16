package com.dearwolves.samplerecords;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.dearwolves.core.interfaces.IMediaService;
import com.dearwolves.core.interfaces.IRestService;
import com.dearwolves.core.interfaces.IStringService;
import com.dearwolves.core.model.MediaResponse;
import com.dearwolves.core.model.SearchRequest;
import com.dearwolves.core.model.dto.responses.ListResponseDto;
import com.dearwolves.core.model.dto.responses.MediaResponseDto;
import com.dearwolves.core.rest.RestRequestCallback;
import com.dearwolves.samplerecords.ui.home.HomeViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTests {


    HomeViewModel viewModel;

    @Mock
    IMediaService mediaService;
    @Mock
    IStringService stringService;
    @Mock
    IRestService restService;

    @Mock
    private LifecycleOwner _lifecycleOwner;
    private LifecycleRegistry _lifecycleRegistry;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        _lifecycleRegistry = new LifecycleRegistry(_lifecycleOwner);
        when(_lifecycleOwner.getLifecycle()).thenReturn(_lifecycleRegistry);
        viewModel = new HomeViewModel(mediaService, stringService);
    }

    @Test
    public void successfulSearchTest() {
        //arrange
        SearchRequest searchRequest = new SearchRequest("star", "au", "movie");
        doAnswer(invocation -> {
            RestRequestCallback<List<MediaResponse>> callback = invocation.getArgument(1, RestRequestCallback.class);
            callback.onSuccess(getSampleResponse());
            return null;
        }).when(mediaService).search(any(SearchRequest.class), any(RestRequestCallback.class));


        viewModel.search(searchRequest);

        viewModel.getChangesNotification().observe(_lifecycleOwner, aVoid -> {
            assert(true);
        });

        _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }


    private List<MediaResponse> getSampleResponse() {
        List<MediaResponse> array = new ArrayList<>();
        array.add(new MediaResponse("Track 1"));
        array.add(new MediaResponse("Track 2"));
        array.add(new MediaResponse("Track 3"));
        return array;
    }

}
