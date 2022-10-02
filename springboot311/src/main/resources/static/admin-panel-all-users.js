let tbodyUsers = document.getElementById('tbody-users')

const getPrinciple=async()=>{
    fetch("http://localhost:8080/api/v1/user/principle")
        .then(async (res)=>{
            let json=await res.json()
            localStorage.setItem('principle',json)
        })
}

getPrinciple()

function authenticateUser(user, password) {
    let token = user + ":" + password;
    let hash = btoa(token);
    return "Basic " + hash;
}

async function loadList() {
    fetch("http://localhost:8080/api/v1/user/all")
        .then(async (response) => {
            let json = await response.json()
            json.forEach(value => {
                let tr = document.createElement('tr')
                tr.innerHTML = `
                                    <td id="td-user-id" >${value.id}</td>
                                    <td id="td-first-name">${value.firstName}</td>
                                    <td id="td-last-name">${value.lastName}</td>
                                    <td id="td-city">${value.userCity}</td>
                                    <td id="td-roles-">
                                        <span>
                                            ${value.roles.map(role => role.value)}
                                        </span>
                                    </td>
                                    <td>
                                        <a type="button" 
                                           class="btn btn-sm btn-info text-white" 
                                           data-bs-toggle="modal"
                                           data-bs-target="#editModal" 
                                           data-id="${value.id}" 
                                           href="${value.id}"
                                           id="${value.id}"
                                           data-target="#edit">Edit</a>
                                    </td>
                                    <td>
                                        <a type="button" 
                                           class="btn btn-sm btn-danger" 
                                           data-bs-toggle="modal"
                                           data-bs-target="#DELETE" 
                                           id="delete-btn"
                                           data-id="${value.id}" 
                                           data-target="#delete">Delete</a>
                                    </td>
                                `
                tbodyUsers.appendChild(tr)
            })
        })
}

const getUser = async (id) => {
    return fetch(`http://localhost:8080/api/v1/user/${id}`)
        .then(async (res) => {
            return await res.json()
        })
}

const getRoles = async () => {
    return fetch(`http://localhost:8080/api/v1/role/`)
        .then(async (res) => {
            return await res.json()
        })
}

loadList().then(() => {
    let editModal = document.getElementById('editModal')
    let editModalBody = document.getElementById('edit-modal-body')

    editModal.addEventListener('shown.bs.modal', async function (ev) {
        let userId = ev.relatedTarget.id
        let user = await getUser(userId)
        let roles = await getRoles()

        let div = document.createElement('div')
        div.innerHTML = `<br>
                    <label for="firstName0"><b></b>First name</label>
                    <input class="form-control" id="firstName0" name="firstName" value="${user.firstName}"/>
                    <br>
                    <label for="lastName0"><b></b>Last Name</label>
                    <input class="form-control" id="lastName0" value="${user.lastName}" name="lastName"/>
                    <br>
                    <label for="userCity0"><b></b>City</label>
                    <input class="form-control" id="userCity0" value="${user.userCity}" name="userCity"/>

                    <br>
                    <label for="password0"><b>Password</b></label>
                    <input  type="text" class="form-control" id="password0" name="password" value="${user.password}"/>
                    <br>
                    <label for="roles"><b>Role</b></label>
                    <select multiple class="form-control form-control-sm" id="edit-modal-select"
                            name="roles" size="2" required>
                             
                    </select>
                    <br><br>
                `
        div.nodeName='btnDivModal'
        let selectEditModal = div.getElementsByTagName('select')[0]
        roles.forEach(role => {
            let selectedRole = !!user.roles.find(value=>value.value===role.value);
                let el = document.createElement("option");
                el.text = role.value.substring(5);
                el.value = role.id;
                if (selectedRole) el.selected = true;
                selectEditModal.appendChild(el)
            }
        )
        const editForm = document.forms["formEditUser"];
        editForm.name=user.id
        editModal.addEventListener('hidden.bs.modal',()=>{
            editModalBody.removeChild(div)
        })
        editModalBody.appendChild(div)
        editUser()
    })

})


const editUser=() =>{
    const editForm = document.forms["formEditUser"];
    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected) editUserRoles.push({
                id : editForm.roles.options[i].value,
                name : "ROLE_" + editForm.roles.options[i].text
            })
        }
        let principle=localStorage.getItem('principle')
        fetch("http://localhost:8080/api/user/" + editForm.name, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Authorization":authenticateUser(principle.firstName,principle.password)
            },
            body: JSON.stringify({
                id: editForm.id.value,
                firstName: editForm.firstName.value,
                lastName: editForm.lastName.value,
                userCity: editForm.userCity.value,
                password: editForm.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            loadList();
        })
    })
}


