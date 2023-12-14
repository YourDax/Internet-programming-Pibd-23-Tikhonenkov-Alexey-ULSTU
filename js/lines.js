import {
    createLine,
    deleteLine,
    getAllItemTypes,
    getAllLines,
    getLine,
    updateLine,
} from "./lines-rest-api";
import {
    cntrls,
    createItemsOption,
    createTableRow,
    imagePlaceholder,
} from "./lines-ui";

async function drawItemsSelect() {
    const genres = await getAllItemTypes();
    cntrls.itemsType.innerHTML = "";
    cntrls.itemsType.appendChild(createItemsOption("Выберите значение", "", true));
    genres.forEach((genre) => {
        cntrls.itemsType.appendChild(createItemsOption(genre.genre, genre.id));
    });
}


async function drawLinesTable() {
    console.info("Try to load data");
    if (!cntrls.table) {
        return;
    }
    const data = await getAllLines();
    const genres = await getAllItemTypes();
    cntrls.table.innerHTML = "";
    data.forEach((item, index) => {
        cntrls.table.appendChild(
            createTableRow(
                item,
                index,
                () => location.assign(`editpage.html?id=${item.id}`),
                () => deleteLine(item.id),
                genres
            ),
        );
    });
}

async function addLine(item,name, rating, release_date, director,image) {
    console.info("Try to add item");
    const data = await createLine(item,name, rating, release_date, director,image);
    console.info("Added");
    console.info(data);
    drawLinesTable();
}

async function editLine(id,item,name, rating, release_date, director,image) {
    console.info("Try to update item");
    const data = await updateLine(id,item,name, rating, release_date, director,image);
    console.info("Updated");
    console.info(data);
    drawLinesTable();
}
async function readFile(file) {
    const reader = new FileReader();

    // создание Promise-объекта для использования функции
    // с помощью await (асинхронно) без коллбэков (callback)
    // https://learn.javascript.ru/promise
    return new Promise((resolve, reject) => {
        // 2. "Возвращаем" содержимое когда файл прочитан
        // через вызов resolve
        // Если не использовать Promise, то всю работу по взаимодействию
        // с REST API пришлось бы делать в обработчике (callback) функции
        // onloadend
        reader.onloadend = () => {
            const fileContent = reader.result;
            // Здесь могла бы быть работа с REST API
            // Чтение заканчивает выполняться здесь
            resolve(fileContent);
        };
        // 3. Возвращаем ошибку
        reader.onerror = () => {
            // Или здесь в случае ошибки
            reject(new Error("oops, something went wrong with the file reader."));
        };
        // Шаг 1. Сначала читаем файл
        // Чтение начинает выполняться здесь
        reader.readAsDataURL(file);
    });
}

// функция для обновления блока с превью выбранного изображения
async function updateImagePreview() {
    // получение выбранного файла
    // возможен выбор нескольких файлов, поэтому необходимо получить только первый
    const file = cntrls.image.files[0];
    // чтение содержимого файла в виде base64 строки
    const fileContent = await readFile(file);
    console.info("base64 ", fileContent);
    // обновление атрибута src для тега img с id image-preview
    cntrls.imagePreview.src = fileContent;
}

export function linesForm() {
    console.info("linesForm");
    drawItemsSelect();
    drawLinesTable();
    // Вызов функции обновления превью изображения при возникновении
    // события oncahnge в тэге input с id image
    cntrls.image.addEventListener("change", () => updateImagePreview());

    cntrls.form.addEventListener("submit", async (event) => {
        console.info("Form onSubmit");
        event.preventDefault();
        event.stopPropagation();
        if (!cntrls.form.checkValidity()) {
            return;
        }
        let imageBase64 = "";
        if (cntrls.imagePreview.src !== imagePlaceholder) {
            const result = await fetch(cntrls.imagePreview.src);
            const blob = await result.blob();
            imageBase64 = await readFile(blob);
        }
        const currentId = cntrls.lineId.value;
        if (!currentId) {
            await addLine(
                cntrls.itemsType.value,
                cntrls.filmname.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                imageBase64,
                
            );
        } else {
            await editLine(
                currentId,
                cntrls.itemsType.value,
                cntrls.title.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                imageBase64,
            );
        }
    });
}


export async function linesPageForm() {
    console.info("linesPageForm");
    drawItemsSelect();
    const goBack = () => location.assign("/admin.html");
    cntrls.image.addEventListener("change", () => updateImagePreview());
    const urlParams = new URLSearchParams(location.search);
    const currentId = urlParams.get("id");
    if (currentId) {
        try {
            const line = await getLine(currentId);
            cntrls.itemsType.value = line.itemsId;
            cntrls.filmname.value = line.name;
            cntrls.rating.value = line.rating;
            cntrls.releaseDate.value = line.release_date;
            cntrls.director.value = line.director;
            cntrls.imagePreview.src = line.image ? line.image : imagePlaceholder;
        } catch {
            goBack();
        }
    }

    cntrls.form.addEventListener("submit", async (event) => {
        console.info("Form onSubmit");
        event.preventDefault();
        event.stopPropagation();
        if (!cntrls.form.checkValidity()) {
            return;
        }
        let imageBase64 = "";
        if (cntrls.imagePreview.src !== imagePlaceholder) {
            // Загрузка содержимого атрибута src тэга img с id image-preview
            // Здесь выполняется HTTP запрос с типом GET
            const result = await fetch(cntrls.imagePreview.src);
            // Получение из HTTP-ответа бинарного содержимого
            const blob = await result.blob();
            // Получение base64 строки для файла
            // Здесь выполняется Promise из функции readFile
            // Promise позволяет писать линейный код для работы с асинхронными методами
            // без использования обработчиков (callback) с помощью await
            imageBase64 = await readFile(blob);
        }
        if (!currentId) {
            await addLine(
                cntrls.itemsType.value,
                cntrls.filmname.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                imageBase64,
            );
        } else {
            await editLine(
                currentId,
                cntrls.itemsType.value,
                cntrls.filmname.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                imageBase64,
            );
        }
        goBack();
    });
}
