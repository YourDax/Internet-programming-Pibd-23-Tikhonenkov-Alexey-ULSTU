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

export function linesForm() {
    console.info("linesForm");
    drawItemsSelect();
    drawLinesTable();
    cntrls.form.addEventListener("submit", async (event) => {
        console.info("Form onSubmit");
        event.preventDefault();
        event.stopPropagation();
        if (!cntrls.form.checkValidity()) {
            return;
        }
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
            );
        }
    });
}

export async function linesPageForm() {
    console.info("linesPageForm");
    drawItemsSelect();
    const goBack = () => location.assign("/admin.html");
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
        if (!currentId) {
            await addLine(
                cntrls.itemsType.value,
                cntrls.filmname.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                cntrls.image,
            );
        } else {
            await editLine(
                currentId,
                cntrls.itemsType.value,
                cntrls.filmname.value,
                cntrls.rating.value,
                cntrls.releaseDate.value,
                cntrls.director.value,
                cntrls.image,
            );
        }
        goBack();
    });
}
