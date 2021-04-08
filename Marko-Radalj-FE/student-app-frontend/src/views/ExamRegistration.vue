<template>
  <div class="submitform">
    <div v-if="!submitted">
    
      

      <div class="form-group">
        <label style="margin:15px">Exams</label>
        <select v-model="examRegistration.exam">
          <option v-for="exam in exams" v-bind:key="exam.examId" :value="exam">
            {{ exam.subject.name }} - {{ exam.professor.firstname}} {{ exam.professor.lastname}}
          </option>
        </select>
      </div>

     
      <div class="form-group">
        <label style="margin:15px">Student</label>
       <select v-model="examRegistration.student">
          <option
            v-for="student in students"
            v-bind:key="student.studentId "
            :value="student"
          >
            {{student.firstname }}  {{student.lastname }} 

          </option>
        </select>
      </div>
      <button
        v-on:click="saveExamRegistration"
        class="btn btn-success"
        style="margin-top:12px"
      >
        Submit
      </button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newRegistration">
        Register another exam
      </button>
      <button
        @click="goToRegistratedExams()"
        class="btn btn-primary"
        style="margin-top:12px"
      >
        List of registrated exams
      </button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "add-professor",
  data() {
    return {
      students: [],
      exams: [],
      examRegistration: {
        examRegistrationId: 0,
        student:"",
        exam:""   
      },

      submitted: false,
    };
  },
  created() {
    this.studentList();
    this.examList();
  },
  methods: {
    studentList() {
      http
        .get("students/getStudents")
        .then((response) => {
          this.students = response.data;
        })
        .catch((err) => console.log("Error while displaying students" + err));
    },
    examList() {
      http
        .get("exam/getExams")
        .then((response) => {
          this.exams = response.data;
        })
        .catch((err) => console.log("Error while displaying exams" + err));
    },
    saveExamRegistration() {
      var data = {
        examRegistrationId: this.examRegistration.examRegistrationId,
        student: this.examRegistration.student,
       exam: this.examRegistration.exam
        
      };

      http
        .post("/registration", data)
        .then((response) => {
          this.examRegistration.examRegistrationId = response.data.examRegistrationId;
          this.examRegistration.student = response.data.student;
          this.examRegistration.exam = response.data.exam;
          console.log(response.data.examRegistrationId);
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    goToRegistratedExams() {
      this.$router.push("/registratedExams");
    },
    newRegistration() {
      this.submitted = false;
      this.examRegistration = {};
    },
  },
};
</script>
<style>
select {
  margin: 50px;
  width: 200px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background: url(https://www.eng.it/resources/images/logo%20eng.png) 96% / 15%
    no-repeat #eee;
}
.submitform {
  max-width: 300px;
  margin: auto;
}
#dropdown {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  transition: all 0.2s;
}
</style>
