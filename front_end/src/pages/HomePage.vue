<template>
  <section class="py-4">
    <div class="container">
      <h1 class="text-center py-5 main_title" v-if="!store.loading">
        My photos
      </h1>
      <div class="my-5 d-flex justify-content-center">
        <form class="d-flex w-50" role="search" @submit.prevent="getPhotos()">
          <input
            class="form-control me-2 rounded-5"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="queryString"
          />
          <button
            class="search_btn d-flex justify-content-center align-items-center"
            type="submit"
          >
            <i class="fa-solid fa-magnifying-glass fa-lg"></i>
          </button>
        </form>
      </div>

      <div class="row" mt-5>
        <div class="d-flex justify-content-center mt-5">
          <LoaderComponent v-if="store.loading" />
        </div>
        <Transition>
          <div v-if="!store.loading && store.photosArray.length == 0">
            <h2 class="main_title text-center">No pictures found</h2>
          </div>
        </Transition>
        <TransitionGroup name="list">
          <div
            v-if="!store.loading"
            class="col-lg-4"
            v-for="(item, index) in store.photosArray"
            :key="item.id"
          >
            <CardComponent :photo="item" class="boxed_card" />
          </div>
        </TransitionGroup>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import { store } from "../store";
import CardComponent from "../components/CardComponent.vue";
import LoaderComponent from "../components/LoaderComponent.vue";
export default {
  name: "HomePage",
  data() {
    return {
      store,
      queryString: "",
    };
  },
  methods: {
    getPhotos() {
      store.loading = true;
      const data = {
        params: {
          q: this.queryString,
        },
      };
      axios.get(store.apiURL + "photos", data).then((response) => {
        store.photosArray = response.data;
        // console.log(store.photosArray);
        // console.log(response.data);
        store.loading = false;
      });
    },
  },
  mounted() {
    this.getPhotos();
  },
  components: { CardComponent, LoaderComponent },
};
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

section {
  margin-top: 70px;
}
.boxed_card {
  -webkit-box-shadow: 0px 10px 13px -7px #717171,
    5px 5px 15px 5px rgba(0, 0, 0, 0);
  box-shadow: 0px 10px 13px -7px #717171, 5px 5px 15px 5px rgba(0, 0, 0, 0);
}
.search_btn {
  background-color: $third-color;
  text-decoration: none;
  color: $bg-color;
  border: none;
  width: 50px;
  height: 50px;
  padding: 1rem;
  border-radius: 50%;
  transition: all 0.3s ease;
  &:hover {
    opacity: 0.8;
  }
}

h1 {
  font-size: 3.5rem;
}
.list-enter-active,
.list-leave-active {
  transition: all 0.2s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(10px);
}

.v-enter-active,
.v-leave-active {
  transition: opacity 0.2s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
