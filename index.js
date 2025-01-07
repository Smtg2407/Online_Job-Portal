document.getElementById('signin-link').addEventListener('click', function() {
    document.getElementById('signin').classList.add('visible');
    document.getElementById('signup').classList.remove('visible');
});

document.getElementById('signup-link').addEventListener('click', function() {
    document.getElementById('signup').classList.add('visible');
    document.getElementById('signin').classList.remove('visible');
});

document.getElementById('signin-back').addEventListener('click', function() {
    document.getElementById('signin').classList.add('visible');
    document.getElementById('signup').classList.remove('visible');
});