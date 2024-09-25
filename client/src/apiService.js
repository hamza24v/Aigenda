const BASE_URL = "http://localhost:8080/api";

async function getAll(endpoint) {
  return fetch(`${BASE_URL}/${endpoint}`).then((response) => {
    if (response.status === 200) {
      return response.json();
    } else {
      return Promise.reject("Unexpected status code: " + response.status);
    }
  });
}

async function get(endpoint, id){
  return fetch(`${BASE_URL}/${endpoint}/${id}`).then((response) => {
    if (response.status === 200) {
      return response.json();
    } else {
      return Promise.reject("Unexpected status code: " + response.status);
    }
  });
}

async function post(endpoint, data, token) {
  return fetch(`${BASE_URL}/${endpoint}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}` 
    },
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status === 200 || response.status === 400) {
      return response.json();
    } else {
      return Promise.reject("Unexpected status code: " + response.status);
    }
  });
}

async function update(endpoint, id, data) {
  return fetch(`${BASE_URL}/${endpoint}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status === 204) {
      return null;
    } else if (response.status === 400) {
      return response.json();
    } else {
      return Promise.reject("Unexpected status code: " + response.status);
    }
  });
}

async function remove(endpoint) {
  return fetch(`${BASE_URL}/${endpoint}`, {
    method: "DELETE",
  }).then((response) => {
    if (response.status === 204) {
      return true
    } else {
      return Promise.reject("Unexpected status code: " + response.status);
    }
  });
}

export default {
  getAll,
  get,
  post,
  update,
  remove
};