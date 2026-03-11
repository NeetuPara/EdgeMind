package com.edgemind.app.viewmodel;

import android.content.Context;
import com.edgemind.app.data.ChatDao;
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
public final class ChatViewModel_Factory implements Factory<ChatViewModel> {
  private final Provider<Context> appContextProvider;

  private final Provider<ChatDao> chatDaoProvider;

  private ChatViewModel_Factory(Provider<Context> appContextProvider,
      Provider<ChatDao> chatDaoProvider) {
    this.appContextProvider = appContextProvider;
    this.chatDaoProvider = chatDaoProvider;
  }

  @Override
  public ChatViewModel get() {
    return newInstance(appContextProvider.get(), chatDaoProvider.get());
  }

  public static ChatViewModel_Factory create(Provider<Context> appContextProvider,
      Provider<ChatDao> chatDaoProvider) {
    return new ChatViewModel_Factory(appContextProvider, chatDaoProvider);
  }

  public static ChatViewModel newInstance(Context appContext, ChatDao chatDao) {
    return new ChatViewModel(appContext, chatDao);
  }
}
