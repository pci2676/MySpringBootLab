import {getCookie} from "./utils/cookie.js";

const signUpBtn = document.querySelector("#sign-up-btn");

function SignUp() {
    const onCreateMemberHandler = () => {
        const formData = new FormData(document.querySelector("#sign-up-form"));
        const memberSignUpRequest = {
            email: formData.get("email"),
            name: formData.get("name"),
            password: formData.get("password")
        };

        console.log(JSON.stringify(memberSignUpRequest))

        fetch("/members/sign-up", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-XSRF-TOKEN": getCookie("XSRF-TOKEN")
            },
            body: JSON.stringify(memberSignUpRequest)
        })
            .then(res => {
                window.location.href = "/";
            })
            .catch(error => {
                alert("가입 실패!");
            })
    };

    const initEventListeners = () => {
        signUpBtn.addEventListener("click", onCreateMemberHandler);
    };

    const init = () => {
        initEventListeners();
    };

    return {
        init
    };
}

const signUp = new SignUp();
signUp.init();
