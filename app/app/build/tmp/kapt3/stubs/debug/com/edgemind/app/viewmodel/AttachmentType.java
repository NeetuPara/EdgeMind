package com.edgemind.app.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.edgemind.app.data.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/edgemind/app/viewmodel/AttachmentType;", "", "(Ljava/lang/String;I)V", "IMAGE", "PDF", "DOCUMENT", "TEXT", "app_debug"})
public enum AttachmentType {
    /*public static final*/ IMAGE /* = new IMAGE() */,
    /*public static final*/ PDF /* = new PDF() */,
    /*public static final*/ DOCUMENT /* = new DOCUMENT() */,
    /*public static final*/ TEXT /* = new TEXT() */;
    
    AttachmentType() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.edgemind.app.viewmodel.AttachmentType> getEntries() {
        return null;
    }
}