const serverUrl = "http://localhost:8081";

export function createLineObject(item,name, rating, release_date, director,image) {
    return {
        itemsId: parseInt(item), 
        name,
        rating,
        release_date,
        director,
        image,
    };
}

export async function getAllItemTypes() {
    const response = await fetch(`${serverUrl}/items`);
    if (!response.ok) {
        throw response.statusText;
    }
    return response.json();
}

export async function getAllLines() {
    const response = await fetch(`${serverUrl}/lines?_expand=items`);
    if (!response.ok) {
        throw response.statusText;
    }
    return response.json();
}

export async function getLine(id) {
    const response = await fetch(`${serverUrl}/lines/${id}?_expand=items`);
    if (!response.ok) {
        throw response.statusText;
    }
    return response.json();
}

export async function createLine(item,name, rating, release_date, director,image) {
    const itemObject = createLineObject(item,name, rating, release_date, director,image);

    const options = {
        method: "POST",
        body: JSON.stringify(itemObject),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
    };

    const response = await fetch(`${serverUrl}/lines`, options);
    if (!response.ok) {
        throw response.statusText;
    }
    return response.json();
}

export async function updateLine(id,item,name, rating, release_date, director,image) {
    const itemObject = createLineObject(item,name, rating, release_date, director,image);

    const options = {
        method: "PUT",
        body: JSON.stringify(itemObject),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
    };

    const response = await fetch(`${serverUrl}/lines/${id}`, options);
    if (!response.ok) {
        throw response.statusText;
    }
    return response.json();
}

export async function deleteLine(id) {
    const options = {
        method: "DELETE",
    };

    const response = await fetch(`${serverUrl}/lines/${id}`, options);
    if (!response.ok) {
        throw response.statusText;
    }
    window.location.reload(true);
    return response.json();
}
