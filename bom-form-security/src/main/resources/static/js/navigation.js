import {initNavigation} from "./utils/templates.js";
import {api} from "./api/index.js";


function Navigation() {

    const onLogoutHandler = () => {
        api.member.logout()
            .then(res => window.location.href = res.url)
            .catch(err => console.log(err));
    };

    const initBtnEventListeners = () => {
        const logoutBtn = document.querySelector("#logout-btn");
        logoutBtn.addEventListener('click', onLogoutHandler);
    };

    const init = () => {
        initNavigation();
        initBtnEventListeners();
    };

    return {
        init
    };
}

const navigation = new Navigation();
navigation.init();