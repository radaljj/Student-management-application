<template>
  <div class="submitform">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Subject name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="subjects.name"
          name="name"
        />
      </div>
      <div class="form-group">
        <label for="description">Subject description</label>
        <input
          type="text"
          class="form-control"
          id="naDescriptionme"
          required
          v-model="subjects.description"
          name="Description"
        />
      </div>
      <div class="form-group">
        <label for="semester">Subject semester</label>
        <input
          type="number"
          class="form-control"
          id="semester"
          required
          v-model="subjects.semester"
          name="semester"
        />
      </div>
      <div class="form-group">
        <label for="yearOfStudy">Year of subject study:</label>
        <input
          type="text"
          class="form-control"
          id="yearOfStudy"
          required
          v-model="subjects.yearOfStudy"
          name="yearOfStudy"
        />
      </div>
      <div class="form-group">
        <label for="esp">Esp</label>
        <input
          type="number"
          class="form-control"
          id="esp"
          required
          v-model="subjects.esp"
          name="esp"
        />
      </div>

      <button
        v-on:click="saveSubject"
        class="btn btn-success"
        style="margin-top:12px"
      >
        Submit
      </button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newSubject">
        Add another subject
      </button>
      <button
        @click="goToSubject()"
        class="btn btn-primary"
        style="margin-top:12px"
      >
        List of subjects
      </button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-subject",
  data() {
    return {
      subjects: {
        idSubject: 0,
        name: "",
        description: "",
        esp: "",
        yearOfStudy: "",
        semester: "",
      },
      submitted: false,
    };
  },
  methods: {
    saveSubject() {
      var data = {
        idSubject: this.subjects.idSubject,
        name: this.subjects.name,
        esp: this.subjects.esp,
        description: this.subjects.description,
        yearOfStudy: this.subjects.yearOfStudy,
        semester: this.subjects.semester,
      };

      http
        .post("/subjects", data)
        .then((response) => {
          this.subjects.idSubject = response.data.idSubject;
          console.log(response.data.idSubject);
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    goToSubject() {
      this.$router.push("/subjects");
    },
    newSubject() {
      this.submitted = false;
      this.subjects = {};
    },
  },
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
