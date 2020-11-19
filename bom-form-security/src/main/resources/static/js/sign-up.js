import {api} from "./api/index.js";

const signUpBtn = document.querySelector("#sign-up-btn");

function SignUp() {
    const onCreateMemberHandler = () => {
        const formData = new FormData(document.querySelector("#sign-up-form"));
        const memberSignUpRequest = {
            email: formData.get("email"),
            name: formData.get("name"),
            password: formData.get("password")
        };

        api.member.signUp(memberSignUpRequest)
            .then(res => window.location.href = "/")
            .catch(err => {
                alert("가입 실패!")
            });
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
