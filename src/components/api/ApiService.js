import { ApiClient } from './ApiClient';

class ApiService {
    constructor(url) {
        this.url = url;
    }

    async getAll(expand) {
        return ApiClient.get(`${this.url}${expand || ''}`);
    }

    async get(id, expand) {
        return ApiClient.get(`${this.url}/${id}${expand || ''}`);
    }

    async create(body) {
        return ApiClient.post(this.url, body);
    }

    async update(id, body) {
        return ApiClient.put(`${this.url}/${id}`, body);
    }

    async delete(id) {
        return ApiClient.delete(`${this.url}/${id}`);
    }
}

export default ApiService;
