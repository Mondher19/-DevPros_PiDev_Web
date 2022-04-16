<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


/**
 * @Route("/userc")
 */
class UsercController extends AbstractController
{
    /**
     * @Route("/test", name="userc_index", methods={"GET"})
     */
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('userc/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="userc_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager ): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {






                            $entityManager->persist($user);

                                $entityManager->flush();

                                return $this->redirectToRoute('userc_index', [], Response::HTTP_SEE_OTHER);
                            }

                            return $this->render('userc/new.html.twig', [
                                'user' => $user,
                                'form' => $form->createView(),
                            ]);
                        }

    /**
     * @Route("/show/{id}", name="userc_show", methods={"GET"})
     */
    public function show(User $user,$id): Response
    {
        $user=$this->getDoctrine()->getRepository(User::class)->find($id);
        return $this->render('userc/list-user.html.twig', [
            'user' => $user,
        ]);
    }
    /**
     * @Route("/showb/{id}", name="userc_showb", methods={"GET"})
     */
    public function showb(User $user,$id): Response
    {
        $user=$this->getDoctrine()->getRepository(User::class)->find($id);
        return $this->render('userc/index.html.twig', [
            'user' => $user,
        ]);
    }
    /**
     * @Route("/listuser",name="listuser")
     */
    public function list()
    {
        $user= $this->getDoctrine()->getRepository(User::class)->listUserByNom();
        return $this->render("userc/list-user.html.twig", array('user'=>$user));
    }

    /**
     * @Route("/edituser/{id}", name="userc_edit")
     */
    public function edit(Request $request, User $user, EntityManagerInterface $entityManager,$id): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('userc_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('userc/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }




    /**
     * @Route("/modifygamescat/{id}",name="modifygamescat")
     */
    public function update(Request $request,$id){
        $gamescat= $this->getDoctrine()->getRepository(User::class)->find($id);
        $form= $this->createForm(User::class,$gamescat);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() ){
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listactualitesf");
        }
        return $this->render("userc/edit.html.twig",array("formmodif"=>$form->createView()));
    }



    /**
     * @Route("/removegamescat/{id}",name="removegamescat")
     */
    public function delete($id){
        $gamescat= $this->getDoctrine()->getRepository(User::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($gamescat);
        $em->flush();
        return $this->redirectToRoute("listactualitesf");
    }
}
