<template>
  <div class="container">
    <h3>All Students</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>First name</th>
          <th>Last name</th>
          <th>Email</th>
          <th>Index number</th>
          <th>Index year</th>
          <th>City</th>
          <th>Address</th>
          <th>Current year of study</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in students" v-bind:key="student.studentId">
          <td>{{ student.studentId }}</td>
          <td>{{ student.firstname }}</td>
          <td>{{ student.lastname }}</td>
          <td>{{ student.email }}</td>
          <td>{{ student.indexNumber }}</td>
          <td>{{ student.indexYear }}</td>
          <td>{{ student.city.cityName }}</td>
          <td>{{ student.address }}</td>
          <td>{{ student.currentYearOfStudy }}</td>
          <td>
            <div v-if="editStudent === student.studentId">
              <input v-model="student.firstname" />
              <input v-model="student.lastname" />
              <input v-model="student.email" />
              <input v-model="student.indexNumber" />
              <input v-model="student.indexYear" />
              <input v-model="student.address" />
              <select v-model="student.city">
                <option
                  v-for="city in cities"
                  v-bind:key="city.cityId"
                  :value="city"
                >
                  {{ city.cityName }}
                </option>
              </select>
              <input v-model="student.currentYearOfStudy" />

              <button
                v-on:click="updateSubject(student)"
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

              <button class="btn btn-primary " v-on:click="editStudent = null">
                X
              </button>
            </div>

            <button
              class="btn btn-primary"
              style="margin-right: 5px"
              v-on:click="editStudent = student.studentId"
            >
              <b-icon-pencil /> Edit
            </button>
            <button class="btn btn-danger" v-on:click="deleteStudent(student)">
              <b-icon-trash /> Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="dugmici">
      <button
        @click="goToAddStudent()"
        class="btn btn-primary "
        style="margin-right:900px"
      >
        Add new student
      </button>
      <button id="desno"
        @click="goToExamRegistration()"
        class="btn btn-primary "
      >
        Exam registration

      </button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import http from "../http-common";
export default {
  name: "StudentComponent",
  data() {
    return {
      cities: [],
      students: [],
      editStudent: null,
    };
  },
  created() {
    this.studentList();
    this.cityList();
  },
  methods: {
    cityList() {
      http
        .get("api/getCities")
        .then((response) => {
          this.cities = response.data;
        })
        .catch((err) => console.log("Error while displaying cities" + err));
    },
    studentList() {
      axios
        .get("http://localhost:8080/students/getStudents", {})
        .then((response) => {
          this.students = response.data;
        })
        .catch((err) => console.log("Error while displaying students " + err));
    },
    deleteStudent: async function(students) {
      await axios.delete(
        "http://localhost:8080/students/" + students.studentId
      );
      window.location.reload();

      this.subjectList();
    },
    updateSubject(student) {
      fetch("http://localhost:8080/students/" + student.studentId, {
        body: JSON.stringify(student),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editStudent = null;
      });
    },

    goToAddStudent() {
      this.$router.push("/addStudent");
    },
     goToExamRegistration() {
      this.$router.push("/examRegistration");
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
.dugmici{


  display: inline-flex;
  
}

</style>
