<template>
  <section id="contacts">
    <div class="container py-4">
      <h1 class="text-center py-5">Contact Me</h1>
      <div class="my-5 col-lg-6 m-auto">
        <ContactForm />
      </div>
    </div>
  </section>
</template>

<script>
import ContactForm from "../components/ContactForm.vue";
import { store } from "../store";
export default {
  name: "Contacts",
  components: { ContactForm },
  data() {
    return {
      store,
    };
  },
};
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

section#contacts {
  margin-top: 70px;
}

h1 {
  color: $third-color;
  font-size: 3rem;
}
div span:nth-child(2) {
  color: $third-color;
  margin-left: 1rem;
  font-weight: 600;
}
</style>
