import {defineStore} from "pinia";

export interface Books{
    id: Number,
    bookName: String,
    img: String,
    count: Number
}

interface State{
    items: Books[];
    isLoading: boolean;
}

export const getBooks = defineStore("books",{
    state: (): State => ({ items: [], isLoading: true }),
    actions: {
        async fetchItems() {
            this.isLoading = true;
            let pageNumber: Number = 1;

            const res = await fetch(
                "http://localhost:8080/catalog/" + pageNumber
            );

            if (res.ok) {
                this.items = await res.json();
            }

            this.isLoading = false;
        },
    },
});