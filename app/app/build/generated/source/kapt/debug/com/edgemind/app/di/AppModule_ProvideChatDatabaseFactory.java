package com.edgemind.app.di;

import android.content.Context;
import com.edgemind.app.data.ChatDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideChatDatabaseFactory implements Factory<ChatDatabase> {
  private final Provider<Context> contextProvider;

  private AppModule_ProvideChatDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ChatDatabase get() {
    return provideChatDatabase(contextProvider.get());
  }

  public static AppModule_ProvideChatDatabaseFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideChatDatabaseFactory(contextProvider);
  }

  public static ChatDatabase provideChatDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideChatDatabase(context));
  }
}
