// Array to store registered users
let registeredUsers = [];

// Redirect to voting.html when the Login button is clicked (only if the user has registered)
document.getElementById('loginButton').addEventListener('click', function() {
    const username = document.getElementById('loginUsername').value.trim();
    const password = document.getElementById('loginPassword').value.trim();

    // Check if fields are empty
    if (!username || !password) {
        alert('Please enter your username and password.');
        return;
    }

    // Check if the user exists and credentials are correct
    const user = registeredUsers.find(user => user.username === username && user.password === password);

    if (user) {
        alert('Login successful!');
        window.location.href = 'voting.html'; // Redirect on successful login
    } else {
        alert('Your username or password is incorrect.');
    }
});

// Redirect to voting.html when the Register button is clicked after successful registration
document.getElementById('registerButton').addEventListener('click', function() {
    const username = document.getElementById('registerUsername').value.trim();
    const email = document.getElementById('registerEmail').value.trim();
    const password = document.getElementById('registerPassword').value.trim();

    // Validate inputs
    if (!username || !email || !password) {
        alert('All fields are required!');
        return;
    }

    // Check if the email or username is already registered
    const isEmailExists = registeredUsers.some(user => user.email === email);
    const isUsernameExists = registeredUsers.some(user => user.username === username);

    if (isEmailExists) {
        alert('This email is already registered.');
        return;
    }

    if (isUsernameExists) {
        alert('This username is already taken.');
        return;
    }

    // Register the user
    registeredUsers.push({ username, email, password });
    alert('Registration successful! You can now log in.');
    showLogin(); // Switch to login form after successful registration
});

// Show the register form and hide the login form
function showRegister() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'block';
}

// Show the login form and hide the register form
function showLogin() {
    document.getElementById('loginForm').style.display = 'block';
    document.getElementById('registerForm').style.display = 'none';
}
