<template>
  <div class="container">
    <h3>All Subjects</h3>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Semester</th>
            <th>esp</th>
            <th>Study year</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="subject in subjects" v-bind:key="subject.idSubject">
            <td>{{ subject.idSubject }}</td>
            <td>{{ subject.name }}</td>
            <td>{{ subject.description }}</td>
            <td>{{ subject.semester }}</td>
            <td>{{ subject.esp }}</td>
            <td>{{ subject.yearOfStudy }}</td>
            <td>
              <div v-if="editSubject === subject.idSubject">
                <input v-model="subject.name" />
                <input v-model="subject.description" />
                <input v-model="subject.esp" />
                <input v-model="subject.yearOfStudy" />

                <button
                  v-on:click="updateSubject(subject)"
                  style="margin:5px"
                  type="button"
                  class="btn btn-primary"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="14"
                    height="14"
                    fill="currentColor"
                    class="bi bi-check"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"
                    />
                  </svg>
                </button>

                <button
                  class="btn btn-primary "
                  v-on:click="editSubject = null"
                >
                  X
                </button>
              </div>

              <button
                class="btn btn-primary "
                style="margin-right:7px"
                v-on:click="editSubject = subject.idSubject"
              >
                <b-icon-pencil /> Edit
              </button>
              <button
                button
                class="btn btn-danger"
                v-on:click="deleteSubject(subject)"
              >
                <b-icon-trash /> Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="dodavanje">
        <button
          @click="goToAddSubject()"
          class="btn btn-primary "
          style="margin-right:1050px"
        >
          Add new subject
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "SubjectComponent",
  data() {
    return {
      subjects: [],
      editSubject: null,
    };
  },
  created() {
    this.subjectList();
  },
  methods: {
    subjectList() {
      axios
        .get("http://localhost:8080/subjects/getSubjects", {})
        .then((response) => {
          this.subjects = response.data;
        })
        .catch((err) => console.log("Error while displaying subjects " + err));
    },
    deleteSubject: async function(subject) {
      await axios.delete("http://localhost:8080/subjects/" + subject.idSubject);
      this.subjectList();
    },

    updateSubject(subject) {
      fetch("http://localhost:8080/subjects/" + subject.idSubject, {
        body: JSON.stringify(subject),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editSubject = null;
      });
    },

    goToAddSubject() {
      this.$router.push("/addSubject");
    },
  },
};
</script>

<style scoped>
.dodavanje {
  padding-top: 20px;
}

input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
