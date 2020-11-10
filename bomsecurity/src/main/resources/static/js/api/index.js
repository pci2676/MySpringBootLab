import {getCookie} from "../utils/cookie.js";

const HEADER = {
    JSON_XSRF_TOKEN: {
        'Content-Type': 'application/json',
        "X-XSRF-TOKEN": getCookie("XSRF-TOKEN") // Spring에서 내려줘서 사용하는 쿠키의 이름과 보내는 헤더의 이름이 다르다.
    }
}

const METHOD = {
    PATCH(data) {
        return {
            method: 'PATCH',
            headers: HEADER.JSON_XSRF_TOKEN,
            body: JSON.stringify({
                ...data
            })
        }
    },
    DELETE() {
        return {
            method: 'DELETE'
        }
    },
    POST(data) {
        return {
            method: 'POST',
            headers: HEADER.JSON_XSRF_TOKEN,
            body: JSON.stringify({
                ...data
            })
        }
    }
};

const isJson = (data) => {
    try {
        const maybeJson = JSON.stringify(data);
        return (typeof maybeJson === "object");
    } catch (e) {
        return false;
    }
}

export const api = (() => {
    const request = (uri, config) => fetch(uri, config);
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => {
        if (isJson(data)) {
            return data.json()
        }
        return data;
    });

    const member = {
        signUp(data) {
            return requestWithJsonData(`/api/members`, METHOD.POST(data))
        },
    };

    return {
        member
    }
})();

export default api