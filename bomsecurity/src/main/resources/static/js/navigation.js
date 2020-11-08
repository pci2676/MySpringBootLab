import {initNavigation} from "./utils/templates.js";

function Navigation() {
    const init = () => {
        initNavigation();
    };

    return {
        init
    };
}

const navigation = new Navigation();
navigation.init();