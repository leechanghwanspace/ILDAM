function confirmDelete(event) {
    if (!confirm('글을 삭제하시겠습니까?')) {
       event.preventDefault();
    }
}