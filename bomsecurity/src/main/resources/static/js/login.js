import {api} from "./api/index.js";

const loginBtn = document.querySelector("#login-btn");

function Login() {
    const onLoginHandler = () => {
        const formData = new FormData(document.querySelector("#login-form"));

        api.member.login(formData)
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.log(err)
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
