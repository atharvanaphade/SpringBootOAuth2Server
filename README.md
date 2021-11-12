## Spring Boot OAuth2 Server

OAuth Architecture : -

![Sys Arch](SysArch.png)

The flow for OAuth2 works as following : -
- The User asks for the auth code on the Authorization server.
- By the Auth Code the user asks for the access token and refresh token to the client API.
- The client API makes request to the resource server, i.e. where the data is stored, if the user is valid and authenticated the data is sent back to the User.
- The Authorization server thus grants access to the User.

To use this OAuth2 server : -

1. Visit the following url `http://localhost:8080/oauth/authorize?client_id=test&response_type=code&scope=user_info<code>`.
2. Put in the credentials in the `src/main/resources/application.properties`.
3. You will receive a code in the url in the form `http://localhost:8080/login?code=<Your_Code>`.
4. Make a cURL request to the following url `http://localhost:8080/oauth/token`, and provide the following headers : the code, grant_type, redirect_uri, scope.
5. On receiving the access token and refresh token you can make requests to the protected api.
6. You can get the access from the refresh token from `http://localhost:8080/oauth/refresh`

#### TODO : -

- To add JDBC support to the client details service so that password and username are saved in a persistent storage instead of `application.properties`.
- Replace deprecated libraries with newer ones.

Completed a TODO? send a PR :)