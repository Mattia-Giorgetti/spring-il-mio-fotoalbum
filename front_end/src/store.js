import { reactive } from "vue";
export const store = reactive({
    apiURL: 'http://localhost:8080/api/',
    photosArray: [],
    isDropDown: false,
    loading: false,
    navbarLinks: [{
        link: 'Home',
        url: 'home'
    },
    {
        link: 'Contacts',
        url: 'contacts'
    }
    ],
});