import { authHeader } from "../helpers";

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
        .then(resp => resp.json())
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
        .then(resp => resp.json())
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
        .then(resp => resp.json())
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

    return fetch('swimple/api/trainings', requestOptions)
        .then(resp => resp.json())
        .then(training => {
            return training;
        }).catch(error => {
            Promise.reject(error);
        });
}