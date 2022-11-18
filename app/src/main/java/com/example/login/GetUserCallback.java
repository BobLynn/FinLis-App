package com.example.login;

//當伺服器端請求完畢，回報通知
interface GetUserCallback {
        public abstract void done(User returnUser);
}
