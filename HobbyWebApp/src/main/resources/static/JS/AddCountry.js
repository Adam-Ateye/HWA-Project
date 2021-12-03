"use strict"

console.log(`AddCountry.js is linked`);

let addCountryName = document.querySelector("#addCountry");
let addLength = document.querySelector("#addLength");
let addDate = document.querySelector("#addDate");
let addCountryButton = document.querySelector("#addCountryButton");
let alertAddCountryDiv = document.querySelector("#alertAddCountryDiv");

let successMessage = (object) => {
    let newDiv = document.createElement("div");

    newDiv.classList = "alert alert-success alrt new-alert";
    newDiv.innerHTML = `<strong>Success<strong><strong>${object.Country}</strong> has been added!`;
    alertAddCountryDiv.appendChild(newDiv);
    
    setTimeout(function () {
        $(".new-alert").fadeOut(400);
    }, 4000);
}

    let failMessage = () => {
        let newDiv = document.createElement("div");

        newDiv.classList = "alert alert-fail alrt new-alert";
        newDiv.innerHTML = `<strong>Unsuccessful!</strong> An error has occured!`;
        alertAddCountryDiv.appendChild(newDiv);

        setTimeout(function () {
            $(".new-alert").fadeOut(400);
        }, 4000);
    };

    let addCountryData = () => {
        let countryFromInput = addCountry.value;
        let dateFromInput = addDate.value;
        let lengthFromInput = addLength.value;

        let newObj = {
            name: countryFromInput,
            date: dateFromInput,
            length: lengthFromInput,
        };

        addCountryToApiFunction(newObj);
    }

    let addCountryToApiFunction = (object) => {
        fetch("http://localhost:9000/country/create", {
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
    };
    addCountryButton.addEventListener("fullscreenchange", addCountryData);
