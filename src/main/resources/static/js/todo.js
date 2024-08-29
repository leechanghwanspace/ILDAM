document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.complete-btn').forEach(button => {
        button.addEventListener('click', function () {
            const todoId = this.getAttribute('data-id');
            fetch(`/todos/complete/${todoId}`)
                .then(response => window.location.reload())
                .catch(error => console.error('Error:', error));
        });
    });

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function () {
            const todoId = this.getAttribute('data-id');
            fetch(`/todos/delete/${todoId}`)
                .then(response => window.location.reload())
                .catch(error => console.error('Error:', error));
        });
    });

    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function () {
            const todoId = this.getAttribute('data-id');
            window.location.href = `/todos/edit/${todoId}`;
        });
    });
});
