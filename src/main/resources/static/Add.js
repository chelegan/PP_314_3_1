const urlNewUSer = 'http://localhost:8080/admin/api/save'
const addUserForm = document.querySelector('#newUser')

const addUsername = document.getElementById('usernameNew')
const addName = document.getElementById('nameNew')
const addLastname = document.getElementById('lastNameNew')
const addAge = document.getElementById('ageNew')
const addEmail = document.getElementById('emailNew')
const addPassword = document.getElementById('passwordNew')
const addRoles = document.getElementById('addUser')

addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const selectedRoles = [];
    const roleOptions = addRoles.options;
    for (let i = 0; i < roleOptions.length; i++) {
        if (roleOptions[i].selected) {
            selectedRoles.push(roleOptions[i].value);
        }
    }

    fetch(urlNewUSer, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: addUsername.value,
            firstName: addName.value,
            lastName: addLastname.value,
            age: addAge.value,
            email: addEmail.value,
            password: addPassword.value,
            roles: selectedRoles
        })
    })
        .then(res => res.json())
        .then(data => {
            users = data;
            getAllUsers(users);
        })
        .then(res => {
            document.getElementById('add_new_user').click()
        })
});
