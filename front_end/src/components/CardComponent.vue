<template>
  <div class="card gap-1 mb-5 border-0">
    <div class="img-wrap">
      <img
        :src="'http://localhost:8080/files/' + photo.id"
        class="card-img-top"
        :alt="photo.title"
      />
    </div>
    <div class="card-body">
      <h5 class="card-title fs-3 my-3">{{ photo.title }}</h5>
      <p class="card-text" v-if="photo.description.length > 0">
        {{ this.trimContent(photo.description) }}
      </p>
      <p v-else>No description available</p>
      <router-link
        class="my-btn rounded-1"
        :to="{ name: 'single-photo', params: { id: photo.id } }"
        >View</router-link
      >
    </div>
  </div>
</template>

<script>
import { store } from "../store";
export default {
  name: "CardComponent",
  props: {
    photo: Object,
  },
  data() {
    return {
      store,
      contentMaxLen: 40,
    };
  },
  methods: {
    trimContent(text) {
      if (text.length > this.contentMaxLen) {
        return text.substr(0, this.contentMaxLen) + "...";
      } else {
        return text;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;
.img-wrap {
  height: 400px;
  overflow: hidden;
}
h5 {
  color: $third-color;
  font-weight: 600;
}
p {
  color: $text-color;
  padding-bottom: 2rem;
}
.my-btn {
  background-color: $primary-color;
  text-decoration: none;
  color: $bg-color;
  padding: 0.4rem 1rem;
  transition: all 0.3s ease;
  &:hover {
    opacity: 0.8;
  }
}
</style>
