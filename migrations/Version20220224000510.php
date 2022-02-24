<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220224000510 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE tournoi (id_tour INT AUTO_INCREMENT NOT NULL, tour INT DEFAULT NULL, nom_tour VARCHAR(255) NOT NULL, desc_tour VARCHAR(255) NOT NULL, nbr_joueur INT NOT NULL, INDEX IDX_18AFD9DF6AD1F969 (tour), PRIMARY KEY(id_tour)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE type_tournoi (id INT AUTO_INCREMENT NOT NULL, nom_type VARCHAR(255) NOT NULL, desc_type VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE tournoi ADD CONSTRAINT FK_18AFD9DF6AD1F969 FOREIGN KEY (tour) REFERENCES type_tournoi (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE tournoi DROP FOREIGN KEY FK_18AFD9DF6AD1F969');
        $this->addSql('DROP TABLE tournoi');
        $this->addSql('DROP TABLE type_tournoi');
    }
}
