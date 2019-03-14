import {authHeader} from "../helpers";
import {userService} from "./user.services";

export const commentService = {
    getAll,
    create
};

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

function getAll(trainingId) {
    const requestOptions = {
        method: "GET",
        headers: authHeader()
    };

    return fetch(`/swimple/api/trainings/${trainingId}/comments`, requestOptions)
        .then(handleResponse)
        .then(comments => {
            return comments;
        });
}

function create(training, body) {
    const requestOptions = {
        method: "POST",
        headers: {
            ...authHeader(),
            'Content-Type': 'application/json'
        },
        body: `{"body": "${body}"}`
    };

    return fetch(`/swimple/api/trainings/${training.id}/comments`, requestOptions)
        .then(handleResponse)
        .then(comment => {
            return comment;
        });
}

