export const cntrls = {
    button: document.getElementById("items-add"),
    table: document.querySelector("#items-table tbody"),
    form: document.getElementById("items-form"),
    lineId: document.getElementById("items-line-id"),
    filmname: document.getElementById("name"),
    itemsType: document.getElementById("item"),
    rating: document.getElementById("rating"),
    releaseDate: document.getElementById("releaseDate"),
    director: document.getElementById("director"),
    image: document.getElementById("image"),
    imagePreview: document.getElementById("image-preview"),
};


export const imagePlaceholder = "https://via.placeholder.com/300x400";

export function createItemsOption(name, value = "", isSelected = false) {
    var today = new Date().toJSON().split('T')[0];
    releaseDate.value = today
    const option = document.createElement("option");
    option.value = value || "";
    option.selected = isSelected;
    option.text = name;
    return option;
}

function createTableAnchor(icon, callback) {
    const i = document.createElement("i");
    i.classList.add("fa-solid", icon);
    const a = document.createElement("a");
    a.href = "#";
    a.appendChild(i);
    a.onclick = (event) => {
        event.preventDefault();
        event.stopPropagation();
        callback();
    };
    const td = document.createElement("td");
    td.appendChild(a);
    return td;
}
function createTableColumn(value) {
    const td = document.createElement("td");
    td.textContent = value;
    return td;
}

export function createTableRow(line, index, editCallback, deleteCallback, items) {
    const rowNumber = document.createElement("th");
    rowNumber.scope = "row";
    rowNumber.textContent = index + 1;
    
    const row = document.createElement("tr");
    row.id = `line-${line.id}`;
    row.appendChild(rowNumber);

    // Добавляем свойства из массива "lines"
    row.appendChild(createTableColumn(line.name)); // Название фильма

    // Добавляем жанр на основе "genre_id" из массива "items"
    const genreItem = items.find(item => item.id === line.itemsId);
    const genre = genreItem ? genreItem.genre : '';
    row.appendChild(createTableColumn(genre)); // Жанр


    row.appendChild(createTableColumn(line.rating)); // Рейтинг
    row.appendChild(createTableColumn(formatDate(line.release_date))); // Дата
    row.appendChild(createTableColumn(line.director)); // Режиссер

    // Добавляем ячейки для редактирования и удаления
    row.appendChild(createTableAnchor("fa-pencil", editCallback));
    row.appendChild(createTableAnchor("fa-trash", deleteCallback));

    return row;
}



// Функция для форматирования даты
function formatDate(dateString) {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
}

