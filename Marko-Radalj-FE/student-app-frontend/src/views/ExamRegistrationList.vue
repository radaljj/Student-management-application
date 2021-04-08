<template>
  <div class="container">
    <h3>All registrated exams</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Student first name</th>
          <th>Student last name</th>
          <th>Registrated exam</th>
          <th>Professor on duty</th>
          <th>Exam date</th>

        </tr>
      </thead>
      <tbody>
        <tr v-for="registratedExam in registratedExams" v-bind:key="registratedExam.examRegistrationId">
          <td>{{ registratedExam.student.firstname }}</td>
          <td>{{ registratedExam.student.lastname }}</td>
        <td>{{ registratedExam.exam.subject.name }}</td>
         <td>{{ registratedExam.exam.professor.firstname }} {{ registratedExam.exam.professor.lastname }}</td>
        <td>{{ registratedExam.exam.period }}</td>

          <td>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <button
        @click="goToRegistratedExams()"
        class="btn btn-primary "
        style="margin-right:1050px"
      >
        Add new exam
      </button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "ExamRegistrationList",
  data() {
    return {
      registratedExams: [],
      editProfessor: null,
    };
  },
  created() {
    this.examList();
   
  },
  methods: {
    examList() {
      axios
        .get("http://localhost:8080/registration/getRegistration", {})
        .then((response) => {
          this.registratedExams = response.data;
        })
        .catch((err) =>
          console.log("Error while displaying registratedExams " + err)
        );
    },

    goToRegistratedExams() {
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


</style>
