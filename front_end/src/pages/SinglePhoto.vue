<template>
  <section id="single_photo" class="py-5">
    <div class="container" v-if="photo">
      <div class="row">
        <div class="col-lg-6">
          <h1 class="mb-5">{{ photo.title }}</h1>
          <p class="border-bottom pb-3" v-if="photo.description.length > 0">
            {{ photo.description }}
          </p>
          <p v-else class="border-bottom pb-3">No description available</p>
          <div class="d-flex gap-4" v-if="photo.categories.length > 0">
            <span class="prefix_span">Categories:</span>
            <ul class="list-unstyled">
              <li v-for="category in photo.categories" class="py-1">
                {{ category.name }}
              </li>
            </ul>
          </div>
          <div v-else class="prefix_span">No Categories</div>
        </div>
        <div class="col-lg-6 mt-sm-5">
          <img class="img-fluid" :src="photo.url" :alt="photo.title" />
        </div>
        <router-link class="my-btn w-25 rounded-1" :to="{ name: 'home' }"
          ><i class="fa-solid fa-arrow-left"></i> Back</router-link
        >
      </div>
    </div>
    <div class="container d-flex justify-content-center" v-else>
      <LoaderComponent />
    </div>
  </section>
</template>

<script>
import axios from "axios";
import LoaderComponent from "../components/LoaderComponent.vue";
import { store } from "../store";
export default {
  name: "SinglePhoto",
  data() {
    return {
      store,
      photo: null,
    };
  },
  methods: {
    getSingleProject() {
      axios
        .get(`${store.apiURL}photos/${this.$route.params.id}`)
        .then((response) => {
          if (response.data) {
            this.photo = response.data;
          } else {
            console.log(this.$router);
            this.$router.push({ title: "not-found" });
          }
          // console.log(this.photo);
          // console.log(response.data);
        });
    },
  },
  mounted() {
    this.getSingleProject();
  },
  components: { LoaderComponent },
};
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

#single_photo {
  margin-top: 70px;

  h1 {
    color: $primary-color;
  }
  .prefix_span {
    color: $secondary-color;
    font-weight: bolder;
  }
  ul li {
    color: $third-color;
  }
  a {
    text-decoration: none;
    color: $primary-color;
    margin-top: 2rem;
    display: block;
    transition: all 0.3s ease;
    &:hover {
      transform: translateX(8px);
    }
  }
}
</style>
