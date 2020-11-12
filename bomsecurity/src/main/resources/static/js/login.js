import {api} from "./api/index.js";

const loginBtn = document.querySelector("#login-btn");

function Login() {
    const onLoginHandler = () => {
        const formData = new FormData(document.querySelector("#login-form"));

        api.member.login(formData)
            .then(res => {
                console.log(res)
                console.log(res.headers)
                if (res.url.includes("?error")) {
                    throw Error("로그인 실패.");
                }
                // window.location.href = res.url;
            })
            .catch(err => {
                alert("로그인 실패!")
            });
    };

    const initEventListeners = () => {
        loginBtn.addEventListener("click", onLoginHandler);
    };

    const init = () => {
        initEventListeners();
    };

    return {
        init
    };
}

const login = new Login();
login.init();
