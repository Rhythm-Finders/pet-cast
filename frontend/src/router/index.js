import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import GatherList from '../views/contents/Gather/GatherList.vue';
import GatherDetail from '../views/contents/Gather/GatherDetail.vue';
import GatherCreate from '../views/contents/Gather/GatherCreate.vue';
import GatherModify from '@/views/contents/Gather/GatherModify.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/gatherlist',
    name: 'gatherlist',
    component: GatherList
  },
  {
    path: '/gatherdetail/:id',
    name: 'gatherdetail',
    component: GatherDetail
  },
  {
    path: '/gathercreate',
    name: 'gathercreate',
    component: GatherCreate
  },
  {
    path: '/gathermodify/:id',
    name: 'gathermodify',
    component: GatherModify
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;