"use strict"

console.log(`AddLogin.js is linked`);

let username = document.querySelector("#userSignUp");
let password = document.querySelector("#password");
let addLoginBtn = document.querySelector("#regbut");

let successMessage = (object) => {
    let newDiv = document.createElement("div");

    newDiv.classList = "alert alert-success alrt new-alert";
    newDiv.innerHTML = `<strong>Success<strong><strong>${object.Country}</strong> has been added!`;
    alertAddCountryDiv.appendChild(newDiv);

    setTimeout(function () {
        $(".new-alert").fadeOut(400);
    }, 4000);
};

let failMessage = () => {
    let newDiv = document.createElement("div");

    newDiv.classList = "alert alert-fail alrt new-alert";
    newDiv.innerHTML = `<strong>Unsuccessful!</strong> An error has occured!`;
    alertAddCountryDiv.appendChild(newDiv);

    setTimeout(function () {
        $(".new-alert").fadeOut(400);
    }, 4000);
};

let addLoginData = () => {
    let usernameFromInput = username.value;
    let passwordFromInput = password.value;

    let newObj = {
        username: usernameFromInput,
        password: passwordFromInput,
    };

    addLogintoApiFunction(newObj);
};

let addLogintoApiFunction = (object) => {
    fetch("http://localhost:9000/login/create", {
        method: "POST",
        headers: {
            "Content-type": "application/JSON",
        },
        body: JSON.stringify(object),
    }).then((response) => {
        if (response.status !== 201) {
            console.error(`Status: ${response.status}`);
            failMessage();
            return;
        }
        successMessage(object);
        response.json().then((data) => {
            console.log(data);
        });
    });
}

addLoginBtn.addEventListener("fullscreenchange", addLoginData);