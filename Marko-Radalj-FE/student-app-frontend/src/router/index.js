import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import SubjectComponent from '../views/SubjectComponent.vue'
import AddSubject from '../views/AddSubject.vue'
import StudentComponent from '../views/StudentComponent.vue'
import ProfessorComponent from '../views/ProfessorComponent.vue'
import AddStudent from '../views/AddStudent.vue'
import AddProfessor from '../views/AddProfessor.vue'
import AddExam from '../views/AddExam.vue'
import ExamComponent from '../views/ExamComponent.vue'
import ExamRegistration from '../views/ExamRegistration.vue'
import ExamRegistrationList from '../views/ExamRegistrationList.vue'









const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/registratedExams',
    name: 'ExamRegistrationList',
    component: ExamRegistrationList
  },
  {
    path: '/students',
    name: 'StudentComponent',
    component: StudentComponent
  },
  {
    path: '/exams',
    name: 'ExamComponent',
    component: ExamComponent
  },
   {
    path: '/addExam',
    name: 'AddExam',
    component: AddExam
  },
  {
    path: '/professors',
    name: 'ProfessorComponent',
    component: ProfessorComponent
  },
  {
    path: '/addProfessor',
    name: 'AddProfessor',
    component: AddProfessor
  },
  {
    path: '/addStudent',
    name: 'AddStudent',
    component: AddStudent
  },
  {
    path: '/addSubject',
    name: 'AddSubject',
    component: AddSubject
  },
  {
    path: '/examRegistration',
    name: 'ExamRegistration',
    component: ExamRegistration
  },
  {
    path: '/subjects',
    name: 'Subjects',
    component: SubjectComponent
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
