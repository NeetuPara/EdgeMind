package com.edgemind.app.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.work.*;
import com.edgemind.app.data.*;
import com.edgemind.app.worker.DownloadWorker;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"TAG", "", "app_release"})
public final class ModelManagerViewModelKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ModelManagerVM";
}