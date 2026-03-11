package com.edgemind.app.viewmodel;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ModelManagerViewModel_Factory implements Factory<ModelManagerViewModel> {
  private final Provider<Context> contextProvider;

  private ModelManagerViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ModelManagerViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static ModelManagerViewModel_Factory create(Provider<Context> contextProvider) {
    return new ModelManagerViewModel_Factory(contextProvider);
  }

  public static ModelManagerViewModel newInstance(Context context) {
    return new ModelManagerViewModel(context);
  }
}
