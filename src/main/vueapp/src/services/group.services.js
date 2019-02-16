import {authHeader} from "../helpers";
import {userService} from "./user.services";

export const groupService = {
    getAll
}

function handleResponse(response) {
    const data = response.json();

    if(!response.ok) {
        if(response.status === 401) {
            userService.logout();
        }

        const error = (data && data.message) || response.statusText;
        return Promise.reject(error);
    }

    return data;
}

function getAll() {
    const requestOptions = {
        method: "GET",
        headers: authHeader()
    }

    return fetch('/swimple/api/groups', requestOptions)
        .then(handleResponse)
        .then(groups => {
            return groups;
        }).catch(error => {
            return Promise.reject(error);
        });
}