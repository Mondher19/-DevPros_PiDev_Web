<?php

namespace App\Entity;

use App\Repository\CategorieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=CategorieRepository::class)
 */
class Categorie
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     */
    private $sujet;
/*
    /**
     * @ORM\OneToMany(targetEntity=actualites::class, mappedBy="typecateg")
     */
   /* private $categ;*/

   /* /**
     * @ORM\OneToMany(targetEntity=Actualites::class, mappedBy="actua")
     */
   /* private $ActualitesType;*/

   /* public function __construct()
    {
        $this->categ = new ArrayCollection();
        $this->ActualitesType = new ArrayCollection();
    }*/


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function getRelation(): ?string
    {
        return $this->relation;
    }

    public function setRelation(string $relation): self
    {
        $this->relation = $relation;

        return $this;
    }
/*
    /**
     * @return Collection<int, actualites>
     */
   /* public function getCateg(): Collection
    {
        return $this->categ;
    }

    public function addCateg(actualites $categ): self
    {
        if (!$this->categ->contains($categ)) {
            $this->categ[] = $categ;
            $categ->setTypecateg($this);
        }

        return $this;
    }

    public function removeCateg(actualites $categ): self
    {
        if ($this->categ->removeElement($categ)) {
            // set the owning side to null (unless already changed)
            if ($categ->getTypecateg() === $this) {
                $categ->setTypecateg(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Actualites>
     */
   /* public function getActualitesType(): Collection
    {
        return $this->ActualitesType;
    }

    public function addActualitesType(Actualites $actualitesType): self
    {
        if (!$this->ActualitesType->contains($actualitesType)) {
            $this->ActualitesType[] = $actualitesType;
            $actualitesType->setActua($this);
        }

        return $this;
    }

    public function removeActualitesType(Actualites $actualitesType): self
    {
        if ($this->ActualitesType->removeElement($actualitesType)) {
            // set the owning side to null (unless already changed)
            if ($actualitesType->getActua() === $this) {
                $actualitesType->setActua(null);
            }
        }

        return $this;
    }*/
}
