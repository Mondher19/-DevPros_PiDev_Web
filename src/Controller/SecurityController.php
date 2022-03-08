<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\PassType;
use App\Form\ResetPassType;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use mysql_xdevapi\Exception;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
class SecurityController extends AbstractController
{
    /**
     * @Route("/login", name="app_login")
     */
    public function login(AuthenticationUtils $authenticationUtils): Response
    {
         if ($this->getUser()) {
             return $this->redirectToRoute('listuser');
         }

        // get the login error if there is one
        $error = $authenticationUtils->getLastAuthenticationError();
        // last username entered by the user
        $lastUsername = $authenticationUtils->getLastUsername();

        return $this->render('security/login.html.twig', ['last_username' => $lastUsername, 'error' => $error]);
    }

    /**
     * @Route("/logout", name="app_logout")
     */
    public function logout(): void
    {
        throw new \Exception('This method can be blank - it will be intercepted by the logout key on your firewall.');
    }
    /**
     *@Route("/oublie_pass", name="app_forgotten_password")
     */
    public function forgottenPass(Request $request, UserRepository $userRepo, MailerInterface $mailer, TokenGeneratorInterface $tokenGenerator) {

$form = $this->createForm(ResetPassType::class);
$form->handleRequest($request);
if ($form->isSubmitted() && $form->isValid()){
    $donnees =$form->getData();
    $user=$userRepo->findOneByEmail($donnees['email']);
    if(!$user){
        $this->addFlash('danger','cettte adresse n\exsite pas');
        return $this->redirectToRoute('app_login');}
        $token= $tokenGenerator->generateToken();
        try {
            $user->setResetToken($token);
            $entityManager =$this->getDoctrine()->getManager();
            $entityManager->flush();
        }catch (\Exception $e){
            $this->addFlash('warning', 'une erreur est servenue : '. $e->getMessage());
            return $this->redirectToRoute('app_login');
        }
        $url = $this->generateUrl('app_reset_password', ['token' => $token]);
        $message = (new TemplatedEmail())
            ->from('prosdev6@gmail.com')
            ->to($user->getMail())
            ->html(
                "<p> bonjour ,</p><p></p> une demande de reintilation de mot de passe a ete effectué pour le le site gamepad.fr.
 veuillez cliquer sur le lien suivant: 127.0.0.1:8000" .$url ."</p>");

        $mailer->send($message);
$this->addFlash('message' , "un e_mail de renitialisation de mot de passe  vous a ete envoye");
return $this->redirectToRoute('app_login');}
return  $this->render('security/pass_oublier.html.twig', ['emailForm' => $form->createView()]);
    }
    /**
     * @Route ("/reset-pass/{token}" , name="app_reset_password")
     */
    public function resetpass(Request $request,$token,UserPasswordEncoderInterface $userPasswordEncoder,EntityManagerInterface $entityManager ){
        $user=$this->getDoctrine()->getManager()->getRepository(User::class)->findytoken($token);
        if (!$user)
        {
            return $this->redirectToRoute("login");
        }
        $form = $this->createForm(PassType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid())
        {
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );
            $entityManager->flush();
            return $this->redirectToRoute("login");
        }
        return $this->render ('security/pass_oublier_form.html.twig', ['form' => $form->createView()]);

    }



}
