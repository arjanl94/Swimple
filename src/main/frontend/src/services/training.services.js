import { authHeader } from "../helpers";
import {userService} from "./user.services";

export const trainingService = {
    getAll,
    get,
    update,
    create,
}

function getAll() {
    const requestOptions = {
        method: "GET",
        headers: authHeader(),
    }

    return fetch('/swimple/api/trainings', requestOptions)
        .then(handleResponse)
        .then(trainings => {
            return trainings;
        });
}

function get(id) {
    const requestOptions = {
        method: "GET",
        headers: authHeader(),
    }

    return fetch(`/swimple/api/trainings/${id}`, requestOptions)
        .then(handleResponse)
        .then(training => {
            return training;
        });
}

function update(id, data) {
    const requestOptions = {
        method: "PUT",
        headers: {
            ...authHeader(),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    return fetch(`/swimple/api/trainings/${id}`, requestOptions)
        .then(handleResponse)
        .then(training => {
            return training;
        }).catch(error => {
            return Promise.reject(error);
        });
}

function create(data) {
    const requestOptions = {
        method: "POST",
        headers: {
            ...authHeader(),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    return fetch('/swimple/api/trainings', requestOptions)
        .then(handleResponse)
        .then(training => {
            return training;
        }).catch(error => {
            return Promise.reject(error);
        });
}

function handleResponse(response) {
    const data = response.json();

    if(!response.ok) {
        if(response.status === 401) {
            // userService.logout();
        }

        const error = (data && data.message) || response.statusText;
        return Promise.reject(error);
    }

    return data;
}