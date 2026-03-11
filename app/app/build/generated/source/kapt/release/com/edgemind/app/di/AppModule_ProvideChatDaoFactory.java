package com.edgemind.app.di;

import com.edgemind.app.data.ChatDao;
import com.edgemind.app.data.ChatDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class AppModule_ProvideChatDaoFactory implements Factory<ChatDao> {
  private final Provider<ChatDatabase> databaseProvider;

  private AppModule_ProvideChatDaoFactory(Provider<ChatDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ChatDao get() {
    return provideChatDao(databaseProvider.get());
  }

  public static AppModule_ProvideChatDaoFactory create(Provider<ChatDatabase> databaseProvider) {
    return new AppModule_ProvideChatDaoFactory(databaseProvider);
  }

  public static ChatDao provideChatDao(ChatDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideChatDao(database));
  }
}
