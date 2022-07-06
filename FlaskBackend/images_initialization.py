import torch
from models import gan_2_4, gan_8_15
import argparse
import scipy.misc
import random

parser = argparse.ArgumentParser()
parser.add_argument("--gan_type", default='')
args = parser.parse_args()


class ImageGenerator(object):
    def __init__(self, gan_type=''):
        self.generator = None
        self.gan_type = gan_type
        if gan_type == 'gan_2':
            self.generator = gan_2_4.generator(class_num=2).cuda()
            self.generator.load_state_dict(torch.load('./pretrained_weights/gan_2.pkl'))
        elif gan_type == 'gan_4':
            self.generator = gan_2_4.generator(class_num=4).cuda()
            self.generator.load_state_dict(torch.load('./pretrained_weights/gan_4.pkl'))
        elif gan_type == 'gan_8':
            self.generator = gan_8_15.generator(attribute_count=8).cuda()
            self.generator.load_state_dict(torch.load('./pretrained_weights/gan_8.pth'))
        elif gan_type == 'gan_15':
            self.generator = gan_8_15.generator(attribute_count=15).cuda()
            self.generator.load_state_dict(torch.load('./pretrained_weights/gan_15.pth'))
        self.generator.eval()

    def generate_database(self):
        if self.gan_type == 'gan_2':
            for idx in range(1000):
                noise = torch.rand(1, 100).cuda()
                for class_index in range(2):
                    label = torch.tensor([[1 if class_index == i else 0 for i in range(2)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(noise, label)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_2/' + get_img_name(idx, label),
                                      sample[0])

        elif self.gan_type == 'gan_4':
            for idx in range(1000):
                noise = torch.rand(1, 100).cuda()
                for class_index in range(4):
                    label = torch.tensor([[1 if class_index == i else 0 for i in range(4)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(noise, label)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_4/' + get_img_name(idx, label),
                                      sample[0])

        elif self.gan_type == 'gan_8':
            for idx in range(200):
                noise = torch.randn(1, 100).cuda()
                for single_attr in range(8):
                    embed = torch.tensor([[1 if single_attr == i else 0 for i in range(8)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(embed, noise)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_8/' + get_img_name(idx, embed), sample[0])
                for multi_attr in range(10):
                    embed = torch.tensor([[random.randint(0, 1) for i in range(8)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(embed, noise)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_8/' + get_img_name(idx, embed), sample[0])

        elif self.gan_type == 'gan_15':
            for idx in range(100):
                noise = torch.randn(1, 100).cuda()
                for single_attr in range(15):
                    embed = torch.tensor([[1 if single_attr == i else 0 for i in range(15)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(embed, noise)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_15/' + get_img_name(idx, embed), sample[0])
                for multi_attr in range(20):
                    embed = torch.tensor([[random.randint(0, 1) for i in range(15)]],
                                         dtype=torch.float).cuda()
                    image_tensor = self.generator(embed, noise)
                    sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
                    scipy.misc.imsave('./face_15/' + get_img_name(idx, embed), sample[0])
        print("finished")

imageGenerator = ImageGenerator(args.gan_type)

def get_img_name(img_idx, embed):
    embed = embed.tolist()
    name = ''
    if img_idx < 10000:
        name += '0'
    if img_idx < 1000:
        name += '0'
    if img_idx < 100:
        name += '0'
    if img_idx < 10:
        name += '0'
    name += str(img_idx)
    name += '_'
    for i in range(len(embed[0])):
        name += str(int(embed[0][i]))
    name += '.png'
    return name

def main():
    imageGenerator.generate_database()

if __name__ == '__main__':
    main()