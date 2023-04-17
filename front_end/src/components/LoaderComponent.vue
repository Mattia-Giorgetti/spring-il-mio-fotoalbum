//
<template>
  <div
    class="spinner-border m-5 colored"
    style="width: 3rem; height: 3rem"
    role="status"
  >
    <span class="visually-hidden">Loading...</span>
  </div>
</template>

//
<script>
export default {
  name: "LoaderComponent",
};
</script>

//
<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

.colored {
  color: $primary-color;
}
</style>
