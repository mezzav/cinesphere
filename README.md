A Kotlin project using [The Movie DB](https://www.themoviedb.org) data. <br>

## Build the environment
Add your API key in your `local.properties` file.
```
TMDB_API_TOKEN=API_KEY
```

## Tech stack & Open-source libraries
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- JetPack
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View, ViewModel, Model)
  - Repositories
  - Dagger Hilt - dependency injection
- [Retrofit2](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Sandwich](https://github.com/skydoves/Sandwich) - constructing lightweight API response and handling error responses