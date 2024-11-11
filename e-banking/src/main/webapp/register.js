const form = document.getElementById('form');
const firstname = document.getElementById('fname');
const lastname = document.getElementById('lname');
const mobilenumber = document.getElementById('mobile');
const email = document.getElementById('email');
const password = document.getElementById('password');
const address = document.getElementById('address');
const gender = document.querySelector('.gender');
const aadharnumber = document.getElementById('aadhar');
const fathername = document.getElementById('fathername');
const age = document.getElementById('age');
const dateofbirth = document.getElementById('dob');
const amount = document.getElementById('amount');

form.addEventListener('submit',(e) => {
    e.preventDefault();

    validateInputs();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = "";
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

const validateInputs = () => {
    const firstnameValue = firstname.value.trim();
    const lastnameValue = lastname.value.trim();
    const mobilenumberValue = mobilenumber.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const addressValue = address.value.trim();
    const aadharnumberValue = aadharnumber.value.trim();
    const fathernameValue = fathername.value.trim();
    const ageValue = age.value.trim();
    const amountValue = amount.value.trim();

    if(firstnameValue === '') {
        setError(firstname, 'Firstname is required');
    } 
    else if (firstnameValue.length>=45) {
        setError(firstname, "Firstname is too long");
    } 
    else {
        setSuccess(firstname);
    }

    if (lastnameValue === '') {
        setError(lastname, 'Lastname is required')
    }
    else if (lastnameValue.length>=45) {
        setError(lastname, 'Lastname is too long')
    }
    else {
        setSuccess(lastname);
    }

    if (mobilenumberValue.length!=10) {
        setError(mobilenumber, 'Mobile Number most be 10 digits')
    }
    else {
        setSuccess(mobilenumber);
    }

    if(emailValue === '') {
        setError(email, 'Email is required');
    } else if (!isValidEmail(emailValue)) {
        setError(email, 'Provide a valid email address');
    } else {
        setSuccess(email);
    }

    if (passwordValue === '') {
        setError(password, 'Password is Required')
    }
    else if (passwordValue.length<=8) {
        setError(password, 'Password is too short')
    }
    else if (passwordValue.length>=45) {
        setError(password, "Password is too long")
    }
    else { 
        setSuccess(password)
    }

    if (addressValue === '') {
        setError(address, 'Address is Required')
    }
    else if (addressValue.length>=45) {
        setError(address, 'Address is too long')
    }
    else {
        setSuccess(address)
    }

    if (aadharnumberValue==='') {
        setError(aadharnumber, 'Aadhar number is required')
    }
    else if (aadharnumberValue.length==12) {
        setSuccess(aadharnumber)
    }
    else {
        setError(aadharnumber, 'aadhar number most be 12 digits')
    }

    if (fathernameValue==='') {
        setError(fathername, 'fathername is required')
    }
    else if (fathernameValue.length>=45) {
        setError(fathername, 'fathername is too long')
    }
    else {
        setSuccess(fathername)
    }

    if (ageValue==='') {
        setError(age, 'age is required')
    }
    else {
        const age1 = Number(age)
        if (age1>18) {
            setSuccess(age)
        }
        else {
            setSuccess(age, 'age most be above 18')
        }
    }

    if (amountValue==='') {
        setError(amount, 'amount is required')
    }
    else if (amountValue.length>3) {
        setSuccess(amount)
    }
    else {
        setError(amount, 'amount is deposited most be 1000 above')
    }

}